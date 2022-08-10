package com.mpfcoding.previewcardcomponent

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.mpfcoding.previewcardcomponent.databinding.PreviewCardBinding

class PreviewCard @JvmOverloads constructor(
    private val ctx: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(ctx, attrs) {

    private var useCaseType: UseCaseType? = UseCaseType.ORDER
    private var id: String? = ""
    private var total: String? = ""
    private var orderNumber: String? = ""
    private var useCaseId: String? = ""
    private var deliveryDate: String? = ""
    private var vendorName: String? = ""
    private var status: String? = ""
    private var updateDate: String? = ""
    private var subject: String? = ""
    private var client: String? = ""
    private var category: String? = ""
    private var resolution: String? = ""
    private var expanded: Boolean = false
    private lateinit var ratingCase: RatingCase
    private lateinit var binding: PreviewCardBinding

    init {
        initialize(attrs = attrs)
    }

    private fun initialize(attrs: AttributeSet?) {
        val attributes = ctx.obtainStyledAttributes(attrs, R.styleable.PreviewCard)

        this.id = attributes.getString(R.styleable.PreviewCard_preview_card_id)
        this.total = attributes.getString(R.styleable.PreviewCard_preview_card_total)
        this.orderNumber = attributes.getString(R.styleable.PreviewCard_preview_card_order_number)
        this.useCaseId = attributes.getString(R.styleable.PreviewCard_preview_card_use_case_id)
        this.deliveryDate = attributes.getString(R.styleable.PreviewCard_preview_card_delivery_date)
        this.vendorName = attributes.getString(R.styleable.PreviewCard_preview_card_vendor_name)
        this.status = attributes.getString(R.styleable.PreviewCard_preview_card_status)
        this.updateDate = attributes.getString(R.styleable.PreviewCard_preview_card_update_date)
        this.subject = attributes.getString(R.styleable.PreviewCard_preview_card_subject)
        this.client = attributes.getString(R.styleable.PreviewCard_preview_card_client)
        this.category = attributes.getString(R.styleable.PreviewCard_preview_card_category)
        this.resolution = attributes.getString(R.styleable.PreviewCard_preview_card_resolution)

        attributes.recycle()
        setupView()
    }

    private fun setupView() {
        val inflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = PreviewCardBinding.inflate(inflater, this@PreviewCard, true)

        initViews()
    }

    private fun initViews() {
        this.id?.let { id(id = it) }
        this.total?.let { total(total = it) }
        this.orderNumber?.let { orderNumber(orderNumber = it) }
        this.useCaseId?.let { useCaseId(useCaseId = it) }
        this.deliveryDate?.let { deliveryDate(deliveryDate = it) }
        this.status?.let { status(status = it) }
        this.updateDate?.let { updateDate(date = it) }
        this.subject?.let { subject(subject = it) }
        this.client?.let { client(client = it) }
        this.category?.let { category(category = it) }
        this.resolution?.let { resolution(resolution = it) }
        this.useCaseType?.let { useCaseType(useCaseType = it) }

        setupListeners()
    }

    private fun useCaseType(useCaseType: UseCaseType): PreviewCard {
        this.useCaseType = useCaseType
        return this
    }

    private fun id(id: String): PreviewCard {
        this.id = id
        return this
    }

    private fun total(total: String?): PreviewCard {
        val currency = "R$"

        this.total = total
        binding.textViewTotalValue.text =
            ctx.getString(R.string.rating_service_total_with_currency, currency, total)
        return this
    }

    private fun orderNumber(orderNumber: String?): PreviewCard {
        this.orderNumber = orderNumber
        return this
    }

    private fun useCaseId(useCaseId: String?): PreviewCard {
        this.useCaseId = useCaseId
        binding.ratingMyDeliveredTvUseCaseId.text = useCaseId
        return this
    }

    private fun vendorNameOrder(vendorName: String?): PreviewCard {
        this.vendorName = vendorName
        binding.textViewDeliveredVendorName.text = vendorName
        return this
    }

    private fun vendorNameTicket(vendorName: String?): PreviewCard {
        this.vendorName = vendorName
        binding.textViewVendorNameService.apply {
            text = ctx.getString(R.string.rating_service_vendor, vendorName)
            isVisible = vendorName.isNullOrEmpty().not()
        }
        return this
    }

    fun deliveryDate(deliveryDate: String?): PreviewCard {
        binding.textViewDelivredOnValue.text = this.deliveryDate
        return this
    }

    private fun updateDate(date: String?): PreviewCard {
        binding.textViewCompletedOnService.text =
            ctx.getString(R.string.rating_service_completed_on, this.updateDate)
        return this
    }

    private fun status(status: String?): PreviewCard {
        this.status = status
        status?.let {
            binding.textViewDeliveredStatus.text = status
        }
        return this
    }

    private fun subject(subject: String): PreviewCard {
        this.subject = subject
        return this
    }

    private fun client(client: String?): PreviewCard {
        this.client = client
        binding.textViewRequestedByService.apply {
            text = ctx.getString(R.string.rating_service_request_by, client)
            isVisible = client.isNullOrEmpty().not()
        }
        return this
    }

    private fun category(category: String?): PreviewCard {
        this.category = category

        category?.let {
            binding.textViewCategoryService.visibility = VISIBLE
            binding.textViewCategoryService.text = it
        }
        return this
    }

    private fun resolution(resolution: String?): PreviewCard {
        this.resolution = resolution
        binding.textViewDescriptonService.text = resolution

        binding.textViewDescriptonService.apply {
            this.post {

                this.maxLines = if (this.lineCount > RESOLUTION_MIN_LINES)
                    RESOLUTION_MIN_LINES
                else
                    this.lineCount

                binding.textViewShowMoreService.isVisible = lineCount > RESOLUTION_MIN_LINES
            }
        }
        return this
    }

    fun prepareViewsByRatingCase(ratingCase: RatingCase): PreviewCard {
        this.ratingCase = ratingCase

        when (ratingCase.useCaseType) {
            UseCaseType.ORDER -> {
                previewCardOrderBuilder()
            }
            UseCaseType.TICKET -> {
                previewCardTicketBuilder()
            }
            else -> {  }
        }
        return this
    }

    private fun previewCardOrderBuilder() {
        binding.containerRatingMyDelivery.isVisible = true
        binding.imgViewIcon.setImageDrawable(
            ContextCompat.getDrawable(
                ctx,
                R.drawable.ic_express_delivery
            )
        )
        status(ctx.getString(R.string.rating_service_status_delivered))
        total(ratingCase.total)
        orderNumber(ratingCase.orderNumber)
        useCaseId(ratingCase.useCaseId)
        deliveryDate(ratingCase.deliveryDate)
        vendorNameOrder(ratingCase.vendorName)
    }

    private fun previewCardTicketBuilder() {
        binding.containerRatingMyService.isVisible = true
        binding.imgViewIcon.setImageDrawable(ContextCompat.getDrawable(ctx, R.drawable.ic_headset))
        status(ctx.getString(R.string.rating_service_status_completed))
        updateDate(ratingCase.updateDate)
        useCaseId(ratingCase.useCaseId)
        vendorNameTicket(ratingCase.vendorName)
        client(ratingCase.client)
        category(ratingCase.category)
        resolution(ratingCase.resolution)
        showMoreVisibility()
    }

    private fun showMoreVisibility() {

        binding.textViewDescriptonService.post {
            val lineCount = binding.textViewDescriptonService.lineCount
            if (lineCount <= 2) {
                binding.textViewDescriptonService.ellipsize = null
            } else {
                binding.textViewDescriptonService.maxLines = RESOLUTION_MIN_LINES
                binding.textViewShowMoreService.visibility = VISIBLE
            }
        }
    }

    private fun setupListeners() {
        binding.textViewShowMoreService.setOnClickListener {
            expanded = !expanded

            binding.textViewDescriptonService.maxLines =
                if (expanded)
                    RESOLUTION_MAX_LINES
                else
                    RESOLUTION_MIN_LINES

            binding.textViewShowMoreService.text =
                if (expanded)
                    ctx.getString(R.string.rating_service_show_less)
                else
                    ctx.getString(R.string.rating_service_show_more)

            binding.textViewShowMoreService.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        }
    }

    companion object {
        const val RESOLUTION_MIN_LINES = 2
        const val RESOLUTION_MAX_LINES = 99
    }
}
